package com.example.moonlight.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.moonlight.R
import com.example.moonlight.databinding.FragmentCalendarBinding
import com.example.moonlight.model.Item
import com.example.moonlight.model.LunAge
import com.prolificinteractive.materialcalendarview.CalendarDay

class CalendarFragment : Fragment() {
    private var _binding: FragmentCalendarBinding?= null
    private val binding get()= _binding!!
    private val model: MainMoonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 오늘 날짜
        val today = CalendarDay.today()
        // 캘린더에 오늘 날짜 자동으로 인식하게
        binding.calendarView.setSelectedDate(CalendarDay.today())
        val year = today.year
        val month = today.month
        val day = today.day
        // 날짜 매개변수로 넣기
        model.getMoons(year, month, day)

        // 캘린더에서 날짜를 터치하면 다시 매개변수 던지기
        binding.calendarView.setOnDateChangedListener{ _, selectedDate, _ ->
            val year = selectedDate.year
            val month = selectedDate.month
            val day = selectedDate.day
            model.getMoons(year, month, day)
        }

        model.moon.observe(viewLifecycleOwner, Observer { response ->
            processMoonData(response)
        })
        // 홈버튼
        binding.homeButton.setOnClickListener {
            findNavController().navigate(R.id.action_calendarFragment_to_mainMoonFragment)
        }
    }

    // ViewModel에서 가져온 데이터를 처리하는 Observer에서 이미지를 설정합니다.
    private fun processMoonData(response: LunAge?) {
        response?.let {
            Log.d("response", it.toString())
            val items = it.body?.items?.item
            Log.i("items", items.toString())
            items?.let { data ->
                Log.i("lunAge", data.lunAge.toString())
                // 사진과 텍스트 변경
                updateUI(data)
            } ?: Log.i("observeError", "No lunar data available.")
        } ?: Log.i("observeError", "Response is null.")
    }

    private fun updateUI(data: Item) {
        ImageUtil.setImageResource(binding.lunAgeImg, data.lunAge)
        // 날짜 TextView에 텍스트 설정
        val dateString = "${data.solYear}년 ${data.solMonth}월 ${data.solDay}일 ${data.solWeek}요일"
        // 요일
        binding.date.text = "$dateString"

        // 월령 TextView에 텍스트 설정
        binding.lunAge.text = "월령: ${data.lunAge}"
        // 달 종류
        binding.moon.text = ImageUtil.setMoonText(data.lunAge)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}