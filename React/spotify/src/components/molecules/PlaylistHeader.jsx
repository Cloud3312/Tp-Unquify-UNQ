import React from 'react'
import './PlaylistHeader.css'

const PlaylistHeader = ({
  name,
  image,
  likeAmount,
  songAmount,
  description,
  authorName,
  duration,
}) => {
  return (
    <>
      <div className='playlistHeaderContainer'>
        <img src={image} alt='Playlist image' />
        <div className='playlistSubHeader'>
          <h4>PLAYLIST</h4>
          <h2>{name}</h2>
          <div className='playlistInfo'>
            <p>{likeAmount} likes</p>
            <p>{songAmount} songs</p>
            <p>{duration}</p>
          </div>
          <div className='playlistInfo'>
            <p>{description}</p>
          </div>
          <div className='playlistInfo'>
            <p>Author: {authorName}</p>
          </div>
        </div>
      </div>
    </>
  )
}

export default PlaylistHeader
