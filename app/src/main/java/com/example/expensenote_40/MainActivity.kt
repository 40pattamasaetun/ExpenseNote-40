package com.example.expensenote_40

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    //สร่างตัวเเปรสำหรับหน้า UI Element
    private lateinit var etAmount: EditText
    private lateinit var etNote: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnClear: Button
    private lateinit var tvLastItem: TextView
    private lateinit var tvTotal: TextView

    //สร้างตัวเเปรสำหรับการเก็บค่ายอดรวม
    private var total: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etAmount = findViewById<EditText>(R.id.etAmount)
        etNote = findViewById<EditText>(R.id.etDescription)
        btnAdd = findViewById<Button>(R.id.btnAdd)
        btnClear = findViewById<Button>(R.id.btnClear)
        tvLastItem = findViewById<TextView>(R.id.tvRecentHeader)
        tvTotal = findViewById<TextView>(R.id.tvRecentItemContent)

        btnAdd.setOnClickListener {
            val amountText = etAmount.text.toString().trim()
            val noteText = etNote.text.toString().trim()

            //ตรวบสอบเงื่อนไข
            if (amountText.isEmpty()) {
                Toast.makeText(this, "กรุณากรอกจำนวนเงิน", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //ตรวจสอบว่าผู้ใช้งานกรอกข้อมูลในช่องเป็นตัวเลขรึป่าว
            val amount = amountText.toDoubleOrNull()
            if (amount == null) {
                Toast.makeText(this, "กรุณากรอกจำนวนเงินเป็นตัวเลข", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //ทำการบวกจำนวนเข้าไปยังยอดเดิม
            total += amount

            val showNote = if (noteText.isEmpty()) noteText else "ไม่ระบุ"

            tvLastItem.text = "รายการล่าสุด: $showNote - %,.2fบาท".format(amount)
            tvTotal.text = "ยอดรวมวันนี้: %,.2f บาท".format(total)

            etAmount.text.clear()
            etNote.text.clear()
        }
        btnClear.setOnClickListener {
            total = 0.0

            etAmount.text.clear()
            etNote.text.clear()
            tvLastItem.text = "รายการล่าสุด: -"
            tvTotal.text = "ยอดรวมวันนี้: 0.00 บาท"
        }
        }
    }