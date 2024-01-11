package dev.xshoxruh.airline_reservation_system.services.imp;

import dev.xshoxruh.airline_reservation_system.config.security.JwtTokenUtil;
import dev.xshoxruh.airline_reservation_system.dtos.auth.AuthUserCreateDto;
import dev.xshoxruh.airline_reservation_system.dtos.auth.Login;
import dev.xshoxruh.airline_reservation_system.entities.Airport;
import dev.xshoxruh.airline_reservation_system.mappers.AgentMapper;
import dev.xshoxruh.airline_reservation_system.entities.AuthUser;
import dev.xshoxruh.airline_reservation_system.entities.AuthUserOtp;
import dev.xshoxruh.airline_reservation_system.mappers.AuthUserMapper;
import dev.xshoxruh.airline_reservation_system.repositories.AgentRepository;
import dev.xshoxruh.airline_reservation_system.repositories.AirportRepository;
import dev.xshoxruh.airline_reservation_system.repositories.AuthUserOTPRepository;
import dev.xshoxruh.airline_reservation_system.repositories.AuthUserRepository;
import dev.xshoxruh.airline_reservation_system.services.AuthUserOtpService;
import dev.xshoxruh.airline_reservation_system.services.AuthUserService;
import dev.xshoxruh.airline_reservation_system.utils.BaseUtils;
import dev.xshoxruh.airline_reservation_system.utils.MailSenderService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl implements AuthUserService {

    private final AuthUserMapper authUserMapper;
    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final BaseUtils baseUtils;
    private final MailSenderService mailSenderService;
    private final AuthUserOTPRepository authUserOTPRepository;
    private final AuthUserOtpService authUserOtpService;
    private final AgentMapper agentMapper;
    private final AgentRepository agentRepository;
    private final AirportRepository airportRepository;

    @Override
    public String register(@NonNull AuthUserCreateDto dto) {
        AuthUser authUser = authUserMapper.toEntity(dto);
        if (authUserRepository.findByEmail(dto.getEmail()).isPresent())
            throw new RuntimeException("Email Already Taken");
        if (authUserRepository.findByUsername(dto.getUsername()).isPresent())
            throw new RuntimeException("Username Already Taken");
        if (!dto.getPassword().equals(dto.getConfirmPassword()))
            throw new RuntimeException("Passwords must be the same.");
        authUser.setRole("CUSTOMER");
        authUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        authUser.setCreatedAt(LocalDateTime.now());
        authUserRepository.save(authUser);
        System.out.println("authUser.getId() = " + authUser.getId());
        AuthUserOtp authUserOTP = authUserOtpService.createOTP(authUser);
        Map<String, String> model = new HashMap<>();
        model.put("to", authUser.getEmail());
        model.put("code",  authUserOTP.getCode());
        model.put("role", "CUSTOMER");
        mailSenderService.sendActivationMail(model);
        return "Success";
    }

    @Override
    public String login(@NonNull Login login) {
        String username = login.getUsername();
        String password = login.getPassword();
        var authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(username);
    }

    @Override
    public String activaceAccount(@NonNull String code) {
        AuthUserOtp otp = authUserOTPRepository.findByCodeIgnoreCase(code)
                .orElseThrow(() -> new RuntimeException("Invalid Code"));
        if (otp.getExpiresAt().isBefore(LocalDateTime.now()))
            throw new RuntimeException("Code is expired");

        Long userID = otp.getUserID();
        authUserRepository.activateUser(userID);
        authUserOTPRepository.deleteById(userID);
        return "Account Successfully Activated";
    }

    @Override
    public List<Airport> getCity(@NonNull String city) {
        List<Airport> list = airportRepository.findByCityIgnoreCaseOrderByIdAsc(city);
        if (list.isEmpty())
            throw new RuntimeException("Not Found");
        return list;
    }

    @Override
    public Airport getAirport(@NonNull Long id) {
        return airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }
}
