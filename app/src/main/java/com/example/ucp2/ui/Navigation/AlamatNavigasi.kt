package com.example.ucp2.ui.Navigation

interface AlamatNavigasi {
    val route: String
}

object DestinasiHomeDosen : AlamatNavigasi {
    override val route = "home"
}

object DestinasiHomeMatkul : AlamatNavigasi {
    override val route = "homeMk"
}


object DestinasiDetail : AlamatNavigasi {
    override val route = "detail"
    const val KDMK = "kdMk"
    val routesWithArg = "$route/{$KDMK}"
}

object DestinasiUpdate : AlamatNavigasi {
    override val route = "update"
    const val KDMK = "kdMk"
    val routesWithArg = "$route/{$KDMK}"
}