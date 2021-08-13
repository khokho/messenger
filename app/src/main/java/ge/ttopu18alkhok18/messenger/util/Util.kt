package ge.ttopu18alkhok18.messenger.util

class Util {

    companion object {
        fun mailToUserName(email: String): String = email.split("@")[0]
    }

}