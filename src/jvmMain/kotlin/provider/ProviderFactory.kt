package provider

interface ProviderFactory<T> {
    fun get(): T
}