package app.joaobfpaulo.pokedex.domain.cache

enum class CacheMode {

    /**
     * Load data from local source.
     */
    CACHE_ONLY,

    /**
     * Load data from local source. If result is empty, update data from remote source and
     * return the updated result when the sync is finished.
     */
    CACHE,

    /**
     * Update data from remote source and return the updated result when the sync is finished.
     */
    SERVER_SYNC
}