package uz.akra.namozvaqtlari.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.akra.namozvaqtlari.databinding.ItemViewpagerBinding
import uz.akra.namozvaqtlari.models.HaftalikModel
import uz.akra.namozvaqtlari.models.MyPagerModel
import uz.akra.namozvaqtlari.retrofit.APIClient
import uz.akra.namozvaqtlari.retrofit.APIService


class MyViewPagerAdapter(private val list: List<HaftalikModel>) : PagerAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemViewPagerBinding =
            ItemViewpagerBinding.inflate(LayoutInflater.from(container.context), container, false)

        itemViewPagerBinding.tvBomdod.text = list[position].times.tong_saharlik









        container.addView(itemViewPagerBinding.root)
        return itemViewPagerBinding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)

    }
}

