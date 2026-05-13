package com.example.billing

class Subscription {
    private var subscriptionId: String? = null
    private var planCode: String? = null
    private var status: String? = null
    private var billingPeriodMonths: Int = 0
    private var autoRenew: Boolean = false
    private var createdAtEpochMillis: Long = 0L
    private var renewsAtEpochMillis: Long = 0L

    fun getSubscriptionId(): String? = subscriptionId
    fun setSubscriptionId(value: String?) { subscriptionId = value }

    fun getPlanCode(): String? = planCode
    fun setPlanCode(value: String?) { planCode = value }

    fun getStatus(): String? = status
    fun setStatus(value: String?) { status = value }

    fun getBillingPeriodMonths(): Int = billingPeriodMonths
    fun setBillingPeriodMonths(value: Int) { billingPeriodMonths = value }

    fun isAutoRenew(): Boolean = autoRenew
    fun setAutoRenew(value: Boolean) { autoRenew = value }

    fun getCreatedAtEpochMillis(): Long = createdAtEpochMillis
    fun setCreatedAtEpochMillis(value: Long) { createdAtEpochMillis = value }

    fun getRenewsAtEpochMillis(): Long = renewsAtEpochMillis
    fun setRenewsAtEpochMillis(value: Long) { renewsAtEpochMillis = value }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Subscription) return false
        return subscriptionId == other.subscriptionId &&
                planCode == other.planCode &&
                status == other.status &&
                billingPeriodMonths == other.billingPeriodMonths &&
                autoRenew == other.autoRenew &&
                createdAtEpochMillis == other.createdAtEpochMillis &&
                renewsAtEpochMillis == other.renewsAtEpochMillis
    }

    override fun hashCode(): Int {
        var result = subscriptionId?.hashCode() ?: 0
        result = 31 * result + (planCode?.hashCode() ?: 0)
        result = 31 * result + (status?.hashCode() ?: 0)
        result = 31 * result + billingPeriodMonths
        result = 31 * result + autoRenew.hashCode()
        result = 31 * result + createdAtEpochMillis.hashCode()
        result = 31 * result + renewsAtEpochMillis.hashCode()
        return result
    }

    override fun toString(): String {
        return "Subscription(subscriptionId=$subscriptionId, planCode=$planCode, " +
                "status=$status, billingPeriodMonths=$billingPeriodMonths, " +
                "autoRenew=$autoRenew, createdAtEpochMillis=$createdAtEpochMillis, " +
                "renewsAtEpochMillis=$renewsAtEpochMillis)"
    }
}
