package com.example.firstexample

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
//import com.example.firstdynamicmodule.ModuleConstant
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus


class MainActivity : AppCompatActivity(), SplitInstallStateUpdatedListener {

    lateinit var button: Button
    lateinit var button2: Button
    lateinit var button1: Button
    lateinit var progressDialog: ProgressDialog
    lateinit var splitInstallManager: SplitInstallManager
    var mysessionid: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressDialog = ProgressDialog(this@MainActivity)
        splitInstallManager = SplitInstallManagerFactory.create(this)
        button = findViewById(R.id.ondemand)
        button1 = findViewById(R.id.uninstall_ondemand)
        button2 = findViewById(R.id.btncheckformodule)


        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                myOnDemandModule()
            }
        })

        button1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                deleteModule()
            }
        })

        button2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                checkModuleInstallOrNot()
            }
        })
    }

    fun deleteModule() {
        //to delete installed module
        splitInstallManager.deferredUninstall(listOf(ClassNameConstant.MODULE_NAME))
    }

    fun myOnDemandModule() {
        //create installation request
        val splitInstallRequest = SplitInstallRequest.newBuilder().addModule(ClassNameConstant.MODULE_NAME).build()


        //this method is used to install on-demand module
        splitInstallManager.startInstall(splitInstallRequest).addOnSuccessListener { sessionId ->
            mysessionid = sessionId
            println("sessiomid $mysessionid")
        }.addOnFailureListener { exception ->

            Toast.makeText(this, "Error installing module..try again!", Toast.LENGTH_SHORT).show()


            //getting error on splitistallexception
            /*when ((exception as SplitInstallException).errorCode) {


            SplitInstallErrorCode.NETWORK_ERROR -> {
            Toast.makeText(this,"NETWORK_ERROR",Toast.LENGTH_SHORT).show()
            }
            SplitInstallErrorCode.ACTIVE_SESSIONS_LIMIT_EXCEEDED -> {
                Toast.makeText(this,"ACTIVE SESSIONS LIMIT EXCEEDED",Toast.LENGTH_SHORT).show()
            }
            SplitInstallErrorCode.ACCESS_DENIED -> {
                TODO()
            }
            SplitInstallErrorCode.API_NOT_AVAILABLE -> {
                TODO()
            }
            SplitInstallErrorCode.APP_NOT_OWNED -> {
                TODO()
            }
            SplitInstallErrorCode.INCOMPATIBLE_WITH_EXISTING_SESSION -> {
                TODO()
            }
            SplitInstallErrorCode.INSUFFICIENT_STORAGE -> {
                TODO()
            }
            SplitInstallErrorCode.INTERNAL_ERROR -> {
                TODO()
            }
            SplitInstallErrorCode.INVALID_REQUEST -> {
                TODO()
            }
            SplitInstallErrorCode.MODULE_UNAVAILABLE -> {
                TODO()
            }
            SplitInstallErrorCode.NO_ERROR -> {
                TODO()
            }
            SplitInstallErrorCode.PLAY_STORE_NOT_FOUND -> {
                TODO()
            }
            SplitInstallErrorCode.SERVICE_DIED -> {
                TODO()
            }
            SplitInstallErrorCode.SESSION_NOT_FOUND -> {
                TODO()
            }
            SplitInstallErrorCode.SPLITCOMPAT_COPY_ERROR -> {
                TODO()
            }
            SplitInstallErrorCode.SPLITCOMPAT_EMULATION_ERROR -> {
                TODO()
            }
            SplitInstallErrorCode.SPLITCOMPAT_VERIFICATION_ERROR -> {
                TODO()
            }
        }*/
        }
        splitInstallManager.registerListener(this)
    }

    override fun onPause() {
        super.onPause()
        //after use or when we  not needed unregister Listener
        splitInstallManager.unregisterListener(this)
    }

    // we get status here of module installation
    override fun onStateUpdate(state: SplitInstallSessionState) {
        if (state.sessionId() == mysessionid) {
            when (state.status()) {
                SplitInstallSessionStatus.DOWNLOADING -> {
                    println("DOWNLOADING")
                    progressDialog.setMessage("DOWNLOADING, please wait")
                    progressDialog.show()
                }
                SplitInstallSessionStatus.INSTALLED -> {
                    var intent: Intent? = null
                    try {
                        intent = Intent(
                            this,
                            Class.forName("com.example.firstdynamicmodule.LoginActivity")
                        )
                        intent.putExtra("key", "123")
                        startActivity(intent)

                    } catch (e: ClassNotFoundException) {
                        e.printStackTrace()
                    }

                    println("INSTALLED")
                    progressDialog.dismiss()
                    splitInstallManager.unregisterListener(this)

                }

                SplitInstallSessionStatus.INSTALLING -> {
                    println("INSTALLING")
                    progressDialog.setMessage("INSTALLING, please wait")
                    progressDialog.show()

                }
                SplitInstallSessionStatus.CANCELED -> {
                    println("CANCELED")
                    progressDialog.setMessage("process CANCELED.. try again")
                    progressDialog.dismiss()
                }
                SplitInstallSessionStatus.PENDING -> {
                    println("PENDING")
                    progressDialog.setMessage("process PENDING.. please wait!")
                    progressDialog.show()
                }
                SplitInstallSessionStatus.CANCELING -> {
                    println("CANCELING")

                }
                SplitInstallSessionStatus.DOWNLOADED -> {
                    println("DOWNLOADED")

                }
                SplitInstallSessionStatus.FAILED -> {
                    println("FAILED")
                }
                SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                    println("REQUIRES_USER_CONFIRMATION")

                }
                SplitInstallSessionStatus.UNKNOWN -> {
                    println("UNKNOWN")

                }
            }

        }
    }


    //check wheather module is install or not... return boolean
    fun checkModuleInstallOrNot() {
        if (splitInstallManager.getInstalledModules().contains(ClassNameConstant.MODULE_NAME)) {
            Toast.makeText(this, "installed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "not installed", Toast.LENGTH_SHORT).show()
        }

    }
}


