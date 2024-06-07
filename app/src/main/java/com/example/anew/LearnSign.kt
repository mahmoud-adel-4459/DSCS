/*package com.example.anew

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.WindowInsets
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LearnSign : AppCompatActivity() {
    private lateinit var textView: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_sign)

        textView = findViewById(R.id.textView)

        textView.let {
            it.text = "LEARN SIGN"
            it.setTextColor(Color.WHITE)
            it.textSize = 48f

            it.setOnApplyWindowInsetsListener { view, insets ->
                // تحويل insets من WindowInsetsCompat إلى WindowInsets
                val convertedInsets = convertToWindowInsets(insets)

                // هنا يمكنك إضافة العمليات التي تريدها لمُستمع OnApplyWindowInsetsListener

                // يجب عادةً إعادة WindowInsets المعالجة (مثل الهامشات) إلى المكان الذي يمكنه الاستفادة منه
                return@setOnApplyWindowInsetsListener view.onApplyWindowInsets(convertedInsets)
            }
        }
    }

    private fun convertToWindowInsets(insets: WindowInsets): WindowInsets {
        // يمكنك هنا تعديل عملية التحويل كما تشاء
        return insets
    }
}
*/



package com.example.anew

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class LearnSign : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button
    private lateinit var button10: Button
    private lateinit var button11: Button
    private lateinit var button12: Button
    private lateinit var button13: Button
    private lateinit var button14: Button
    private lateinit var button15: Button
    private lateinit var button16: Button
    private lateinit var button17: Button
    private lateinit var button18: Button
    private lateinit var button19: Button
    private lateinit var button20: Button
    private lateinit var button21: Button
    private lateinit var button22: Button
    private lateinit var button23: Button
    private lateinit var button24: Button
    private lateinit var button25: Button
    private lateinit var button26: Button
    private lateinit var floatingActionButton3 : FloatingActionButton
    private lateinit var floatingActionButton4 : FloatingActionButton



    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_sign)

        textView = findViewById(R.id.textView)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)
        button10 = findViewById(R.id.button10)
        button11 = findViewById(R.id.button11)
        button12 = findViewById(R.id.button12)
        button13 = findViewById(R.id.button13)
        button14 = findViewById(R.id.button14)
        button15 = findViewById(R.id.button15)
        button16 = findViewById(R.id.button16)
        button17 = findViewById(R.id.button17)
        button18 = findViewById(R.id.button18)
        button19 = findViewById(R.id.button19)
        button20 = findViewById(R.id.button20)
        button21 = findViewById(R.id.button21)
        button22 = findViewById(R.id.button22)
        button23 = findViewById(R.id.button23)
        button24 = findViewById(R.id.button24)
        button25 = findViewById(R.id.button25)
        button26 = findViewById(R.id.button26)
        floatingActionButton3 = findViewById(R.id.floatingActionButton3)
        floatingActionButton4 = findViewById(R.id.floatingActionButton4)





        textView.apply {
            text = "LEARN SIGN"
            setTextColor(Color.WHITE)
            textSize = 48f
        }

        button1.setOnClickListener {
            val intent = Intent(this@LearnSign, a::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val intent = Intent(this@LearnSign, b::class.java)
            startActivity(intent)
        }
        button3.setOnClickListener {
            val intent = Intent(this@LearnSign, c::class.java)
            startActivity(intent)
        }
        button4.setOnClickListener {
            val intent = Intent(this@LearnSign, d::class.java)
            startActivity(intent)
        }
        button5.setOnClickListener {
            val intent = Intent(this@LearnSign, e::class.java)
            startActivity(intent)
        }
        button6.setOnClickListener {
            val intent = Intent(this@LearnSign, f::class.java)
            startActivity(intent)
        }
        button7.setOnClickListener {
            val intent = Intent(this@LearnSign, g::class.java)
            startActivity(intent)
        }
        button8.setOnClickListener {
            val intent = Intent(this@LearnSign, h::class.java)
            startActivity(intent)
        }
        button9.setOnClickListener {
            val intent = Intent(this@LearnSign, i::class.java)
            startActivity(intent)
        }
        button10.setOnClickListener {
            val intent = Intent(this@LearnSign, j::class.java)
            startActivity(intent)
        }
        button11.setOnClickListener {
            val intent = Intent(this@LearnSign, k::class.java)
            startActivity(intent)
        }
        button12.setOnClickListener {
            val intent = Intent(this@LearnSign, l::class.java)
            startActivity(intent)
        }
       button13.setOnClickListener {
            val intent = Intent(this@LearnSign, m::class.java)
            startActivity(intent)
        }
        button14.setOnClickListener {
            val intent = Intent(this@LearnSign, n::class.java)
            startActivity(intent)
        }
        button15.setOnClickListener {
            val intent = Intent(this@LearnSign, o::class.java)
            startActivity(intent)
        }
        button16.setOnClickListener {
            val intent = Intent(this@LearnSign, p::class.java)
            startActivity(intent)
        }
        button17.setOnClickListener {
            val intent = Intent(this@LearnSign, q::class.java)
            startActivity(intent)
        }
        button18.setOnClickListener {
            val intent = Intent(this@LearnSign, r::class.java)
            startActivity(intent)
        }
        button19.setOnClickListener {
            val intent = Intent(this@LearnSign, s::class.java)
            startActivity(intent)
        }
        button20.setOnClickListener {
            val intent = Intent(this@LearnSign, t::class.java)
            startActivity(intent)
        }
        button21.setOnClickListener {
            val intent = Intent(this@LearnSign, u::class.java)
            startActivity(intent)
        }
        button22.setOnClickListener {
            val intent = Intent(this@LearnSign, v::class.java)
            startActivity(intent)
        }
        button23.setOnClickListener {
            val intent = Intent(this@LearnSign, w::class.java)
            startActivity(intent)
        }
        button24.setOnClickListener {
            val intent = Intent(this@LearnSign, x::class.java)
            startActivity(intent)
        }
        button25.setOnClickListener {
            val intent = Intent(this@LearnSign, y::class.java)
            startActivity(intent)
        }
        button26.setOnClickListener {
            val intent = Intent(this@LearnSign, z::class.java)
            startActivity(intent)
        }

        floatingActionButton3.setOnClickListener {
            val intent = Intent(this@LearnSign, FirstScreen::class.java)
            startActivity(intent)
        }
        floatingActionButton4.setOnClickListener {
            val intent = Intent(this@LearnSign, gl::class.java)
            startActivity(intent)
        }

    }
}


