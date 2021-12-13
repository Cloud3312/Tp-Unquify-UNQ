import React from 'react'
import utils from 'utils/utils'
import Song from '../atoms/Song'
import './SongTable.css'

const SongTable = ({ songs }) => {
  return (
    <div className='SongTableContainer'>
      <div className='playlistSongsContainer'>
        <div className='songDescriptionHeader'>
          <p className='songIndex'>#</p>
          <p className='songArtist'>Artist</p>
          <p className='songTitle'>Title</p>
        </div>
        <p className='aashe'>⌚</p>
      </div>
      <div className='row'>
        {songs.map((song, index) => {
          return (
            <Song
              key={`${index}. ${song.id}`}
              index={index + 1}
              title={song.name}
              artist={song.band}
              duration={utils.shortFormattedDuration(song.duration)}
            />
          )
        })}
      </div>
    </div>
  )
}

export default SongTable
