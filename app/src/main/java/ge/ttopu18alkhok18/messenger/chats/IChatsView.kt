package ge.ttopu18alkhok18.messenger.chats

import ge.ttopu18alkhok18.messenger.models.Chat

interface IChatsView {

    fun displayChats(chats: List<Chat>)

}