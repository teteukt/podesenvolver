package br.com.podesenvolver.presentation

import android.content.Context
import android.content.Intent
import br.com.podesenvolver.presentation.episode.EpisodeActivity
import br.com.podesenvolver.presentation.podcastDetail.PodcastDetailActivity

fun Context.intentPodcastDetail(url: String) = Intent(this, PodcastDetailActivity::class.java).apply {
    putExtra(EXTRA_PODCAST_URL, url)
}

fun Context.intentEpisode(id: String) = Intent(this, EpisodeActivity::class.java).apply {
    putExtra(EXTRA_EPISODE_ID, id)
}