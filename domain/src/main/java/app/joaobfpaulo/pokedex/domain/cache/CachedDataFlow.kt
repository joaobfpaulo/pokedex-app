package app.joaobfpaulo.pokedex.domain.cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

/**
 * Manage data request from local and remote sources
 */
fun <T> cachedDataFlow(
    cacheMode: CacheMode,
    isCached: suspend () -> Boolean,
    isExpired: suspend () -> Boolean = { false },
    getAllCached: suspend suspend FlowCollector<T>.() -> Unit,
    serverSync: suspend FlowCollector<T>.() -> Unit
): Flow<T> = flow {
    when (cacheMode) {
        CacheMode.CACHE_ONLY -> getAllCached()

        CacheMode.CACHE -> {
            if (isCached() && !isExpired()) {
                getAllCached()
            } else {
                serverSync()
                getAllCached()
            }
        }

        CacheMode.SERVER_SYNC -> {
            serverSync()
            getAllCached()
        }
    }
}