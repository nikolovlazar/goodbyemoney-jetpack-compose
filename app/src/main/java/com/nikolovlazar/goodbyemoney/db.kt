package com.nikolovlazar.goodbyemoney

import com.nikolovlazar.goodbyemoney.models.Category
import com.nikolovlazar.goodbyemoney.models.Expense
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

val config = RealmConfiguration.Builder(
    schema = setOf(Expense::class, Category::class)
)
    .schemaVersion(2) // Sets the new schema version to 2
    .build()

val db: Realm = Realm.open(config)