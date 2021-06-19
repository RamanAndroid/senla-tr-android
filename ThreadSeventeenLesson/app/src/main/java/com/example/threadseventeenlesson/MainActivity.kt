package com.example.threadseventeenlesson

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import com.example.threadseventeenlesson.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val messagesBuffer = mutableListOf<String>()
    private val numbersBuffer = mutableListOf<Double>()
    private val text: StringBuilder = StringBuilder()
    private val handler = Handler(Looper.getMainLooper())
    private var firstThread: Thread = Thread()
    private var secondThread: Thread = Thread()
    private var thirdThread: Thread = Thread()
    private var fourThread: Thread = Thread()
    private val lock = Object()
    private var killThread = 0


    @Volatile
    private var isWork = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firstThread = Thread {

            while (isWork) {
                Thread.sleep(500)
                for (i in 1..100) {
                    appendMessage("Поток номер:$i\n")
                }
                for (i in messagesBuffer) {
                    synchronized(lock) {
                        Log.d("THREAD-1", i)
                        text.append(i)
                    }

                }
                handler.post {
                    Log.d("TEXT", text.toString())
                    binding.threadOne.append(text.toString())
                }
            }
        }




        secondThread = Thread {
            while (isWork) {
                Thread.sleep(500)
                for (i in 1..100) {
                    calculate(i.toDouble())
                }
                for (i in numbersBuffer) {
                    Log.d("THREAD-2", i.toString())
                    text.append("$i\n")
                }
            }
        }

        fourThread = Thread {
            Log.d("THREAD-4", "ПОТОК ЗАПУСТИЛСЯ!")
            while (isWork) {
                synchronized(lock) {
                    lock.wait()
                    if (isWork) {
                        Log.d("THREAD-4", "YUP!")
                        text.append("YUP!\n")

                    }
                }
            }
        }

        thirdThread = Thread {
            while (isWork) {
                Thread.sleep(1000)

                Log.d("THREAD-3", killThread.toString())
                killThread++
                text.append("$killThread")
                if (killThread == 10) {
                    isWork = false
                    synchronized(lock) {
                        lock.notify()
                    }
                    firstThread.join()
                    secondThread.join()
                    fourThread.join()

                }

                handler.post {
                    if (killThread == 10) {
                        binding.start.isEnabled = true
                        Log.d("THREAD-3", "УБИВАЕМ ПОТОКИ")
                    }

                }
            }

        }







        binding.start.setOnClickListener {
            binding.start.isEnabled = false
            startThread()
        }


    }


    private fun appendMessage(message: String) {
        synchronized(lock) {
            messagesBuffer.add(message)
        }
    }


    private fun calculate(number: Double) {
        synchronized(lock) {
            numbersBuffer.add(number + 1 + 1 + 1 + 1 + 1 * 5 * 3 / 2)
            lock.notify()
        }
    }

    private fun startThread() {
        isWork = true
        firstThread.start()
        secondThread.start()
        thirdThread.start()
        fourThread.start()
    }


}