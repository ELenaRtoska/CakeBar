package com.example.cakebar

import android.annotation.SuppressLint
import android.content.Context
import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import io.realm.Realm
import io.realm.RealmConfiguration


@SuppressLint("Registered")

private const val SCHEMA_VERSION: Long = 7

open class MyApplication : MultiDexApplication() {

    companion object {

        fun initRealm(context: Context) {
            //Initialize Realm. Should only be done once when the application starts. The Realm file will be located in Context.getFilesDir() with name "eld.realm"
            Realm.init(context)
            //Configure realm for the application
            Realm.removeDefaultConfiguration()
            val realmConfiguration = RealmConfiguration.Builder()
                .schemaVersion(SCHEMA_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build()
            Realm.setDefaultConfiguration(realmConfiguration)
        }
    }

    override fun onCreate() {
        super.onCreate()



        //Use this only for testing purpose - comment this segment when generating signed APK
        setStethoDebugTool()

        initRealm(this)

    }

    private fun setStethoDebugTool() {
        val realmInspector = RealmInspectorModulesProvider.builder(this)
            .withDeleteIfMigrationNeeded(true)
            .build()

        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .enableWebKitInspector(realmInspector)
                .build()
        )
    }
}
