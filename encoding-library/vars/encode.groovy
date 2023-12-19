import java.util.Base64;

def encode(String message) {
    Base64.Encoder encoder = Base64.getEncoder();
    return encoder.encodeToString(message.getBytes())
}