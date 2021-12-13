import { PlayerContext } from 'context/playerContext'
import { UserContext } from 'context/userContext'
import React, { useContext, useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import spotifyApi from 'services/spotifyApi'
import boton from '../../assets/botonPlay.png'
import likeButton from '../../assets/like.svg'
import trashButton from '../../assets/trash.png'
import utils from '../../utils/utils'
import PlaylistHeader from '../molecules/PlaylistHeader'
import SongTable from '../molecules/SongTable'
import './PlaylistView.css'

const PlaylistView = () => {
  const [playlist, setPlaylist] = useState({
    id: '',
    name: '',
    description: '',
    image: '',
    songs: [],
    author: {},
    userLikes: [],
    duration: '',
  })
  const { user, setUser } = useContext(UserContext)
  const { setPlayer } = useContext(PlayerContext)

  const navigate = useNavigate()
  let { playlistId } = useParams()

  useEffect(() => {
    spotifyApi
      .playlist(playlistId)
      .then((playlist) => {
        setPlaylist(playlist)
      })
      .catch(() => {
        navigate('/404')
      })
  }, [])

  const updateLikes = () => {
    spotifyApi
      .playlistAddOrRemove(playlistId)
      .then((userData) => {
        setUser({ ...user, playlistLikes: userData.playlistLikes })
        setPlaylist({
          ...playlist,
          userLikes: playlist.userLikes.concat({
            name: userData.displayName,
            id: userData.id,
            image: userData.image,
          }),
        })
      })
      .catch((error) => {
        console.error(error)
        navigate('/404')
      })
  }

  const removePlaylist = () => {
    spotifyApi
      .playlistAddOrRemove(playlistId)
      .then((userData) => {
        setUser({
          ...user,
          playlistLikes: userData.playlistLikes,
        })
        setPlaylist({
          ...playlist,
          userLikes: playlist.userLikes.filter(
            (likingUser) => likingUser.id !== userData.id
          ),
        })
      })
      .catch((error) => {
        console.error(error)
        navigate('/404')
      })
  }

  return (
    <>
      <div className='playlistHeader'>
        <PlaylistHeader
          name={playlist.name}
          image={playlist.image}
          likeAmount={playlist.userLikes.length}
          songAmount={playlist.songs.length}
          description={playlist.description}
          authorName={playlist.author.name}
          duration={utils.longFormattedDuration(playlist.duration)}
        />
      </div>

      <div>
        <img
          onClick={() => {
            setPlayer({
              songs: playlist.songs,
              start: 1,
            })
          }}
          src={boton}
          alt=''
          className='playButton'
        />
        {user.playlistLikes.some((p) => p.name === playlist.name) ? (
          <img
            src={trashButton}
            alt=''
            className='trashButton'
            onClick={removePlaylist}
          />
        ) : (
          <img
            src={likeButton}
            alt=''
            className='playButton likeButton'
            onClick={updateLikes}
          />
        )}
      </div>

      <SongTable songs={playlist.songs} />
    </>
  )
}

export default PlaylistView
