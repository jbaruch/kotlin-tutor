package com.integrations.auth

class ServiceCredential {
    private var serviceId: String? = null
    private var clientSecret: String? = null
    private var accessToken: String? = null
    private var expiresAtEpochSeconds: Long = 0L

    fun getServiceId(): String? = serviceId
    fun setServiceId(value: String?) { serviceId = value }

    fun getClientSecret(): String? = clientSecret
    fun setClientSecret(value: String?) { clientSecret = value }

    fun getAccessToken(): String? = accessToken
    fun setAccessToken(value: String?) { accessToken = value }

    fun getExpiresAtEpochSeconds(): Long = expiresAtEpochSeconds
    fun setExpiresAtEpochSeconds(value: Long) { expiresAtEpochSeconds = value }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ServiceCredential) return false
        return serviceId == other.serviceId &&
               accessToken == other.accessToken &&
               expiresAtEpochSeconds == other.expiresAtEpochSeconds
    }

    override fun hashCode(): Int {
        var result = serviceId?.hashCode() ?: 0
        result = 31 * result + (accessToken?.hashCode() ?: 0)
        result = 31 * result + expiresAtEpochSeconds.hashCode()
        return result
    }

    override fun toString(): String =
        "ServiceCredential(serviceId=$serviceId, clientSecret=<redacted>, accessToken=<redacted>, expiresAtEpochSeconds=$expiresAtEpochSeconds)"
}
