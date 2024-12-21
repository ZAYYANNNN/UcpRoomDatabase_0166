package com.example.ucp2.ui.Navigation

interface AlamatNavigasi {
    val route: String
}

object DestinasiHomeDosen : AlamatNavigasi {
    override val route = "home"
}

object DestinasiDetailDosen : AlamatNavigasi {
    override val route = "detail"
    const val NIDN = "Nidn"
    val routesWithArg = "$route/{$NIDN}"
}
