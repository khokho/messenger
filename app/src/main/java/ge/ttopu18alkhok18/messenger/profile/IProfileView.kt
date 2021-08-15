package ge.ttopu18alkhok18.messenger.profile

import ge.ttopu18alkhok18.messenger.models.User

interface IProfileView {
    fun displayProfile(profile: User)
    fun uploadSucceeded()
    fun uploadFailed()
}