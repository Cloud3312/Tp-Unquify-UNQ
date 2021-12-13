import { UserContext } from 'context/userContext'
import React, { useContext, useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import spotifyApi from 'services/spotifyApi'
import CardRow from '../molecules/CardRow'
import './UserView.css'

const UserView = () => {
  const [user, setUser] = useState({
    name: '',
    id: '',
    image: '',
    playlists: [],
    playlistLikes: [],
  })

  const navigate = useNavigate()
  let { userId } = useParams()
  const userFromContext = useContext(UserContext).user

  useEffect(() => {
    if (userId) {
      spotifyApi
        .userById(userId)
        .then((user) => {
          setUser(user)
        })
        .catch(() => {
          navigate('/404')
        })
    } else {
      setUser(userFromContext)
    }
  }, [])

  return (
    <div>
      <div className='user-profile'>
        <div>
          <img
            src={user.image}
            className='user-profile-img'
            onError={(e) => {
              e.target.onerror = null
              e.target.src =
                'https://th.bing.com/th/id/R.b7c4f685abd6967a8692b20119a86a1f?rik=7VOBMKh5r%2b9LLw&riu=http%3a%2f%2fquincyquarry.com%2fwp-content%2fuploads%2f2014%2f08%2fanonymous.png&ehk=8v%2fEdHkgUKDhK%2b1CsPNxolXMlQlaPnaVfCge9BfPG7I%3d&risl=&pid=ImgRaw&r=0'
            }}
          ></img>
        </div>
        <div className='user-info'>
          <div>
            <h2>{user.name}</h2>
          </div>
          <div className='user-info-stats'>
            <p>{user.playlists.length} public playlists</p>
            <span></span>
            <p>Gave {user.playlistLikes.length} likes</p>
            <p>
              Received{' '}
              {user.playlists
                .map((playlist) => playlist.userLikes.length)
                .reduce((l1, l2) => l1 + l2, 0)}{' '}
              likes
            </p>
          </div>
        </div>
      </div>

      <CardRow
        items={user.playlists}
        title='Own playlists'
        circle={false}
        type='playlist'
      />

      <CardRow
        items={user.playlistLikes}
        title='My liked playlists'
        circle={true}
        type='playlist'
      />
    </div>
  )
}

export default UserView
