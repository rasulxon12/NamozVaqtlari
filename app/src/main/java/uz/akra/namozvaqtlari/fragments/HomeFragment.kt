package uz.akra.namozvaqtlari.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.akra.namozvaqtlari.R
import uz.akra.namozvaqtlari.databinding.FragmentHomeBinding
import uz.akra.namozvaqtlari.models.MyNVKunlik
import uz.akra.namozvaqtlari.retrofit.APIClient

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "HomeFragment"


class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }

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

            binding.mySpin.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    binding.myPrBar.visibility = View.VISIBLE
                    loadData()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

        return binding.root
    }

    private fun loadData(){
        APIClient.apiService.getNVkunlik(binding.mySpin.selectedItem.toString())
            .enqueue(object : Callback<MyNVKunlik> {
                override fun onResponse(
                    call: Call<MyNVKunlik>,
                    response: Response<MyNVKunlik>
                ) {
                    if (response.isSuccessful){
                        Log.d(TAG, "onResponse: ${response.body()}")
                        val list = response.body()
                        binding.myPrBar.visibility = View.GONE
                        val rb = response.body()
                        val times = rb?.times

                        binding.apply {

                            tvBomdod.text = times?.tong_saharlik
                            tvTong.text = times?.quyosh
                            tvPeshin.text = times?.peshin
                            tvAsr.text = times?.asr
                            tvShom.text = times?.shom_iftor
                            tvXufton.text = times?.hufton

                            tvRegion.text = rb?.region

                            tvSana.text = rb?.hijri_date?.day.toString()
                            tvDate.text = rb?.date
                            tvHaftakun.text = rb?.weekday
                            tvOy.text = rb?.hijri_date?.month

                            btnHaftalik.setOnClickListener {
                                findNavController().navigate(R.id.weekFragment)
                            }

                            btnOylik.setOnClickListener {
                                findNavController().navigate(R.id.monthFragment)
                            }

                        }

                    }
                }

                override fun onFailure(call: Call<MyNVKunlik>, t: Throwable) {
                    Log.d(TAG, "onFailure: Xatolikka tushdi: ${t.message}")
                    Toast.makeText(context, "Xatolikka tushdi", Toast.LENGTH_SHORT).show()
                }
            })

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}