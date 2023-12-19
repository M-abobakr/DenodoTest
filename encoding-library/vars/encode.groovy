def encode(message: String) {
    return message.getBytes("UTF-8").encodeBase64().toString()
}