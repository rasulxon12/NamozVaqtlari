package uz.akra.namozvaqtlari.utils

import uz.akra.namozvaqtlari.models.MyPagerModel

object MyPagerData {

    val list = ArrayList<MyPagerModel>()

    fun addPages() {
        list.add(MyPagerModel("Dushanba"))
        list.add(MyPagerModel("Seshanba"))
        list.add(MyPagerModel("Chorshanba"))
        list.add(MyPagerModel("Payshanba"))
        list.add(MyPagerModel("Juma"))
        list.add(MyPagerModel("Shanba"))
        list.add(MyPagerModel("Yakshanba"))
    }

}