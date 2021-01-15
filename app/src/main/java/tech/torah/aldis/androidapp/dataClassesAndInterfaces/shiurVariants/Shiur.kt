package tech.torah.aldis.androidapp.dataClassesAndInterfaces.shiurVariants

open class Shiur(val baseId: String?, val baseTitle: String?, val baseLength: String?, val baseSpeaker: String?)
//Not called id, title, etc. because of JVM override issues.