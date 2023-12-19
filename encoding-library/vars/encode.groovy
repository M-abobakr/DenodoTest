def encode(String message) {
    return message.getBytes("UTF-8").encodeBase64().toString()
}