package com.example.firstdynamicmodule.di

import android.content.Context
import com.example.firstdynamicmodule.LoginActivity



interface ApplicationComponent {

    //fun inject(baseClass: BaseClass)

    fun inject(loginActivity: LoginActivity)

  /*  @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(loginModuleDependencies: AppModule): Builder
        fun build(): ApplicationComponent
    }*/
}