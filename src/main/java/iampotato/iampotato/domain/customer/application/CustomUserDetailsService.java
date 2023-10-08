package iampotato.iampotato.domain.customer.application;

import iampotato.iampotato.domain.customer.dao.CustomerRepository;
import iampotato.iampotato.domain.owner.dao.OwnerRepository;
import iampotato.iampotato.domain.owner.domain.Owner;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final CustomerRepository userRepository;
    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findById(username)
                .map(this::createUserDetails)
                .orElseGet(() -> ownerRepository.findById(username)
                        .map(this::createOwnerDetails)
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저")));
    }

    private UserDetails createUserDetails(iampotato.iampotato.domain.customer.domain.Customer user) {
//        System.out.println(user.getLoginId());
//        System.out.println(user.getPassword());
//        System.out.println(user.getNickname());

        return User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(user.getRoles().toArray(new String[0]))
                .build();
    }

    private UserDetails createOwnerDetails(Owner owner) {

        return User.builder()
                .username(owner.getUsername())
                .password(passwordEncoder.encode(owner.getPassword()))
                .roles(owner.getRoles().toArray(new String[0]))
                .build();
    }
}