package com.example.anew

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Suppress("USELESS_ELVIS")
class two_hand : AppCompatActivity() {

    private lateinit var floatingActionButton5 : FloatingActionButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two_hand)

        floatingActionButton5 = findViewById(R.id.floatingActionButton5)

        // الحصول على مرجع TextView من ملف XML
        val leafInfo: TextView = findViewById(R.id.twolastleaf)

        // إعداد الاتصال بقاعدة بيانات Firebase
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val ref: DatabaseReference = database.getReference("binary_values")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                val errorMessage: String = error.message ?: "Unknown error"
                Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_LONG).show()
            }

            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                val binary_values: String? = snapshot.getValue(String::class.java)

                // تحويل النص إلى مصفوفة
                val inputString = binary_values ?: "no Data"
                val binaryArray = inputString.toCharArray().map { it.toString().toInt() }

                // قائمة الكلمات، يجب أن يكون عددها مساويًا لعدد الأوراق في الشجرة (2^10 = 1024)
                val words = listOf(
                    "Apple", "Banana", "Cherry", "Date", "Elderberry", "Fig", "Grape", "Honeydew",
                    "Iced-tea", "Jackfruit", "Kiwi", "Lemon", "Mango", "Nectarine", "Orange", "Papaya",
                    // أضف بقية الكلمات حتى يكون عددها 2^10 = 1024
                    "Quince", "Raspberry", "Strawberry", "Tomato", "Ugli fruit", "Vanilla", "Watermelon", "Xigua",
                    "Yam", "Zucchini"
                ).let { it + List(1024 - it.size) { index -> "Word${index + it.size}" } }

                // بناء الشجرة بعمق 10 وتضمين الكلمات
                val treeRoot = buildTree(10, words)

                // مقارنة المسار مع المصفوفة
                val result = comparePathAndArray(treeRoot, binaryArray)

                if (result != null) {
                    val (path, leaf) = result
                    val leafValue = leaf?.word // هنا نحفظ الكلمة بدلاً من قيمة الورقة
                    leafInfo.text = leafValue ?: "No matching leaf found."
                } else {
                    leafInfo.text = "No matching leaf found."
                }
            }
        })

        floatingActionButton5.setOnClickListener {
            val intent = Intent(this@two_hand, gl::class.java)
            startActivity(intent)
        }
    }

    data class TreeNode(val value: Int, var word: String? = null) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    // دالة بناء الشجرة
    fun buildTree(depth: Int, words: List<String>): TreeNode? {
        if (depth == 0) return null
        val root = TreeNode(0)
        var nodes = listOf(root)
        var wordIndex = 0

        for (i in 1 until depth) {
            val nextNodes = mutableListOf<TreeNode>()
            for (node in nodes) {
                node.left = TreeNode(0)
                node.right = TreeNode(1)
                nextNodes.add(node.left!!)
                nextNodes.add(node.right!!)
            }
            nodes = nextNodes
        }

        // تعيين الكلمات إلى الأوراق في المستوى الأخير
        for (node in nodes) {
            if (wordIndex < words.size) {
                node.word = words[wordIndex]
                wordIndex++
            } else {
                break
            }
        }

        return root
    }

    private fun findLeaf(root: TreeNode?, path: List<Int>): TreeNode? {
        var node = root
        for (direction in path) {
            node = if (direction == 0) node?.left else node?.right
            if (node == null) return null
        }
        return node
    }

    fun comparePathAndArray(root: TreeNode?, array: List<Int>): Pair<List<Int>, TreeNode?>? {
        val path = array.subList(0, array.size - 1)
        val leaf = findLeaf(root, path)
        return if (leaf != null && leaf.value == array.last()) {
            Pair(path, leaf)
        } else {
            null
        }
    }
}