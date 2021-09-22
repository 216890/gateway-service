package pl.lodz.p.stanczyk.gatewayservice.adapter

class ServiceClientException(message: String?, val status: Int) : Exception(message)
