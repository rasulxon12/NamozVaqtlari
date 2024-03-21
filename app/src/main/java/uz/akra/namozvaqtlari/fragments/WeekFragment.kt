package uz.akra.namozvaqtlari.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.akra.namozvaqtlari.R
import uz.akra.namozvaqtlari.adapters.MyViewPagerAdapter
import uz.akra.namozvaqtlari.databinding.FragmentWeekBinding
import uz.akra.namozvaqtlari.models.HaftalikModel
import uz.akra.namozvaqtlari.retrofit.APIClient
import uz.akra.namozvaqtlari.utils.MyPagerData

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "WeekFragment"


class WeekFragment : Fragment() {
    private val binding by lazy { FragmentWeekBinding.inflate(layoutInflater) }
    lateinit var myViewPagerAdapter: MyViewPagerAdapter
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        MyPagerData.addPages()

        binding.myPrBar.visibility = View.VISIBLE
        loadData()






        return binding.root

    }

    private fun loadData(){

        APIClient.apiService.getNVHaftalik("Toshkent")
            .enqueue(object : Callback<List<HaftalikModel>>{
                override fun onResponse(
                    call: Call<List<HaftalikModel>>,
                    response: Response<List<HaftalikModel>>
                ) {
                    if (response.isSuccessful){
                        val list = response.body()

                        binding.myPrBar.visibility = View.GONE
                        myViewPagerAdapter = MyViewPagerAdapter(list!!)
                        binding.myViewpager.adapter = myViewPagerAdapter

                        binding.myViewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
                            override fun onPageScrolled(
                                position: Int,
                                positionOffset: Float,
                                positionOffsetPixels: Int
                            ) {

                            }

                            override fun onPageSelected(position: Int) {
                                when(position){
                                    0->binding.tvHaftakun.text = "Dushanba"
                                    1->binding.tvHaftakun.text = "Seshanba"
                                    2->binding.tvHaftakun.text = "Chorshanba"
                                    3->binding.tvHaftakun.text = "Payshanba"
                                    4->binding.tvHaftakun.text = "Juma"
                                    5->binding.tvHaftakun.text = "Shanba"
                                    6->binding.tvHaftakun.text = "Yakshanba"
                                }
                            }

                            override fun onPageScrollStateChanged(state: Int) {

                            }
                        })


                    }



                }

                override fun onFailure(call: Call<List<HaftalikModel>>, t: Throwable) {
                    Toast.makeText(context, "Hatolikka tushdi", Toast.LENGTH_SHORT).show()
                }
            })




    }




    companion object {


        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WeekFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}