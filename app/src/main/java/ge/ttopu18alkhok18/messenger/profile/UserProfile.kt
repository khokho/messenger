package ge.ttopu18alkhok18.messenger.profile

import android.net.Uri

data class UserProfile(
    var username: String,
    var profilePicURL: Uri?,
    var job: String
)