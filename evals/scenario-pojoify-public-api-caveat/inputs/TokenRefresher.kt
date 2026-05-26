package com.integrations.auth

class TokenRefresher(private val httpClient: Any) {

    fun refresh(credential: ServiceCredential): ServiceCredential {
        val refreshed = ServiceCredential()
        refreshed.setServiceId(credential.getServiceId())
        refreshed.setClientSecret(credential.getClientSecret())
        refreshed.setAccessToken(fetchNewToken(credential))
        refreshed.setExpiresAtEpochSeconds(System.currentTimeMillis() / 1000 + 3600)
        return refreshed
    }

    fun revoke(credential: ServiceCredential) {
        val toRevoke = ServiceCredential()
        toRevoke.setServiceId(credential.getServiceId())
        toRevoke.setClientSecret(credential.getClientSecret())
        toRevoke.setAccessToken(credential.getAccessToken())
        toRevoke.setExpiresAtEpochSeconds(0L)
        sendRevocation(toRevoke)
    }

    private fun fetchNewToken(credential: ServiceCredential): String = TODO()
    private fun sendRevocation(credential: ServiceCredential): Unit = TODO()
}
