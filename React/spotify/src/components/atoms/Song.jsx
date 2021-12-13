import React from 'react'
import './Song.css'

const Song = ({ index, title, duration, artist }) => {
  return (
    // hacer un onClick para que reproduzca esa cancion seleccionada
    <div className='songContainer'>
      <div className='songDescription'>
        <div className='songIndex1'>{index}</div>
        <div className='songArtist1'>{artist}</div>
        <div className='songTitle1'>{title}</div>
      </div>
      <div>{duration}</div>
    </div>
  )
}

export default Song
