package com.samiu.wangank.utils

/**
 * @author samiu 2023/2/6
 * @email samiuzhong@outlook.com
 */
class Constants {

    internal interface Network {
        companion object {
            const val BASE_URL = "https://www.wanandroid.com"
            const val CACHE_SIZE = 25 * 1024 * 1024L    // 25 MiB
            const val TIMEOUT = 15L

            const val PAGER_INITIAL_PAGE = 0
            const val PAGER_SIZE = 20

            const val PAGE = "page"
        }
    }

    internal interface Database {
        companion object {
            const val DATABASE_NAME = "wan_android_db"
            const val ARTICLE_TABLE = "article_table"
            const val ARTICLE_REMOTE_KEYS_TABLE = "article_remote_keys_table"
        }
    }
}