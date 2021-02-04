package id.indosw.igdownloader.interfaces

import id.indosw.igdownloader.model.story.TrayModel

interface UserListInterface {
    fun userListClick(position: Int, trayModel: TrayModel?)
}