package com.example.moonlight.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.moonlight.R
import com.example.moonlight.databinding.FragmentCalendarBinding
import com.example.moonlight.databinding.FragmentMainMoonBinding
import com.example.moonlight.model.Item
import com.example.moonlight.model.LunAge
import java.util.Calendar

class MainMoonFragment : Fragment() {
    private val model: MainMoonViewModel by viewModels()

    private var _binding: FragmentMainMoonBinding?= null
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainMoonBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.moon.observe(viewLifecycleOwner) { response ->
            Log.i("tag", response.toString())
            processMoonData(response)
        }
        // 달력 날짜
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // Calendar.MONTH는 0부터 시작하므로 +1 해줌
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        model.getMoons(year, month, day)
        // 달력 버튼
        binding.currentMonth.setOnClickListener {
            findNavController().navigate(R.id.action_mainMoonFragment_to_calendarFragment)
        }

        // 토글 버튼으로 월령 정보 표기
        binding.toggleSwitch.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked){
                binding.moonPhaseInfo.visibility = View.VISIBLE
            }else{
                binding.moonPhaseInfo.visibility = View.GONE
            }
        }
    }

    private fun updateUI(data: Item) {
        ImageUtil.setImageResource(binding.imageViewLunAge, data.lunAge)
        val dateString = "${data.solYear}년 ${data.solMonth}월 ${data.solDay}일 ${data.solWeek}요일"
        binding.todayDate.text = "오늘 날짜: $dateString"
        binding.todayLunAge.text = "오늘 월령: ${data.lunAge}"
        binding.moon.text = ImageUtil.setMoonText(data.lunAge)
    }

    private fun processMoonData(response: LunAge?) {
        response?.let {
            Log.d("response", it.toString())
            val items = it.body?.items?.item
            items?.let { data ->
                updateUI(data)
            } ?: Log.i("observeError", "No lunar data available.")
        } ?: Log.i("observeError", "Response is null.")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}