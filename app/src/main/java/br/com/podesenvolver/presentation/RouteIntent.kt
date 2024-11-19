package br.com.podesenvolver.presentation

import android.content.Context
import android.content.Intent
import br.com.podesenvolver.presentation.episode.EpisodeActivity
import br.com.podesenvolver.presentation.podcastDetail.PodcastDetailActivity

fun Context.intentPodcastDetail(podcastId: Long) = Intent(this, PodcastDetailActivity::class.java).apply {
    putExtra(EXTRA_PODCAST_URL, podcastId)
}

fun Context.intentEpisode(podcastId: Long, episodeId: Long) = Intent(this, EpisodeActivity::class.java).apply {
    putExtra(EXTRA_PODCAST_ID, podcastId)
    putExtra(EXTRA_EPISODE_ID, episodeId)
}
