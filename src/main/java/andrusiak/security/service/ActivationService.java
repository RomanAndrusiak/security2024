package andrusiak.security.service;

import andrusiak.security.domain.model.Activation;
import andrusiak.security.repository.ActivationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivationService {
    private final ActivationRepository activationRepository;
    private final UserService userService;


    public String generateCode(Long userId) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        int size = (int) (Math.random() * 6 + 10);
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < size; i++) {
            int index = (int) (Math.random() * alphabet.length());
            stringBuilder.append(alphabet.charAt(index));
        }
        Activation activation = new Activation(stringBuilder.toString(), userId);
        activationRepository.save(activation);
        return stringBuilder.toString();
    }

    public void activate(String key) {
        Activation activation = activationRepository.findByKey(key).orElseThrow();
        userService.activate(activation.getUserId());
    }

}
