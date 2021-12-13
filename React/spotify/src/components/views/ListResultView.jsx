import CardRow from 'components/molecules/CardRow'
import SongTable from 'components/molecules/SongTable'
import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import spotifyApi from 'services/spotifyApi'
import './ListResultView.css'

const ListResultView = () => {
  const navigate = useNavigate()

  let { searchText } = useParams()

  const [togglePlaylists, setTogglePlaylists] = useState(false)
  const [toggleUsers, setToggleUsers] = useState(false)
  const [toggleSongs, setToggleSongs] = useState(false)

  const [filteredData, setFilteredData] = useState({
    playlists: [],
    users: [],
    songs: [],
  })

  useEffect(() => {
    spotifyApi
      .searcher(searchText)
      .then((data) => {
        setFilteredData(data)
      })
      .catch(() => {
        navigate('/404')
      })
  }, [searchText])

  return (
    <>
      <div className='listResultContainer'>
        <div>
          <h3
            className='resultsTitle'
            onClick={() => setTogglePlaylists(!togglePlaylists)}
          >
            Playlists ({filteredData.playlists.length} results)
          </h3>
          {togglePlaylists && filteredData.playlists.length !== 0 ? (
            <CardRow
              items={filteredData.playlists}
              title=''
              circle={false}
              type='playlist'
            />
          ) : null}

          <h3
            className='resultsTitle'
            onClick={() => setToggleUsers(!toggleUsers)}
          >
            Users ({filteredData.users.length} results)
          </h3>
          {toggleUsers && filteredData.users.length !== 0 ? (
            <CardRow
              items={filteredData.users}
              title=''
              circle={true}
              type='user'
            />
          ) : null}

          <h3
            className='resultsTitle'
            onClick={() => setToggleSongs(!toggleSongs)}
          >
            Songs ({filteredData.songs.length} results)
          </h3>
        </div>
        {toggleSongs && filteredData.songs.length !== 0 ? (
          <SongTable songs={filteredData.songs} />
        ) : null}
      </div>
    </>
  )
}

export default ListResultView
