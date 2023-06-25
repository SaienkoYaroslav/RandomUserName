package com.example.randomusername

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.randomusername.databinding.ActivityMainBinding
import java.util.UUID
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: AdapterUser
    private val randomList = mutableListOf<RandomUser>()
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBtnsMinusAndPlus()
        setAdapter()
        binding.btnGenerate.setOnClickListener {
            if (binding.etCount.text.toString().isNullOrBlank()) {
                Toast.makeText(application, "Enter number", Toast.LENGTH_SHORT).show()
            } else {
                generateRandomUsername()
            }
        }

    }

    private fun generateRandomUsername() {
        val inputNumber = binding.etCount.text.toString().toInt()

        //алгоритм 1
        val randomString1 = UUID.randomUUID().toString().substring(0, inputNumber)
        //алгоритм 2
        val randomString2 =
            ('A'..'z').map { it }.shuffled().subList(0, inputNumber).joinToString("")
        //алгоритм 3
        val characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
        val usernameBuilder = StringBuilder()
        val random = Random
        repeat(inputNumber) {
            val randomIndex = random.nextInt(characters.length)
            val randomChar = characters[randomIndex]
            usernameBuilder.append(randomChar)
        }
        val randomString3 = usernameBuilder.toString()
        //алгоритм 4
        val wordsList = listOf(
            "A",
            "E",
            "O",
            "unicorn",
            "metal",
            "black",
            "rainbow",
            "thresh",
            "the",
            "apple",
            "banana",
            "cat",
            "dog",
            "elephant",
            "flower",
            "guitar",
            "happiness",
            "boss",
            "man",
            "girl",
            "boy",
            "cool",
            "red",
            "sweet",
            "fast",
            "slow",
            "deep"
        )
        val maxLength = inputNumber * 2
        val filteredWords = wordsList.filter { it.length <= maxLength }
        var username = ""
        var totalLength = 0

        while (totalLength < maxLength && username.length < inputNumber) {
            val randomWord = filteredWords.random()
            if (totalLength + randomWord.length <= maxLength) {
                username += randomWord
                totalLength += randomWord.length
            }
        }
        val randomString4 = username.take(inputNumber).replaceFirstChar { it.uppercase() }


        binding.tvRandomUserName.text = randomString4
        randomList.add(RandomUser(id, randomString4))
        id++
        randomList.sortByDescending { it.id }
        adapter.setList(randomList)
    }

    private fun setAdapter() {
        val list = listOf<RandomUser>()
        adapter = AdapterUser(list)
        binding.rvListRandomUserNames.adapter = adapter
        binding.rvListRandomUserNames.layoutManager = LinearLayoutManager(this)
    }

    private fun setBtnsMinusAndPlus() {
        var userNumber = 0

        binding.btnMinus.setOnClickListener {
            if (binding.etCount.text.toString().isNullOrBlank()) {
                userNumber = 0
            } else {
                userNumber = binding.etCount.text.toString().toInt()
            }
            if (userNumber > 1) {
                binding.etCount.setText("${userNumber - 1}")
                userNumber--
            }
        }

        binding.btnPlus.setOnClickListener {
            if (binding.etCount.text.toString().isNullOrBlank()) {
                userNumber = 0
            } else {
                userNumber = binding.etCount.text.toString().toInt()
            }

            binding.etCount.setText("${userNumber + 1}")
            userNumber++

        }

    }


}