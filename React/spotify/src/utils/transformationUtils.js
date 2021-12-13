export const shortUserFrom = (shortUserData) => {
  return {
    name: shortUserData.displayName,
    id: shortUserData.id,
    image: shortUserData.image,
  }
}

export const playlistFrom = (playlistData) => {
  return {
    id: playlistData.id,
    name: playlistData.name,
    description: playlistData.description,
    image: playlistData.image,
    songs: playlistData.songs,
    author: shortUserFrom(playlistData.author),
    lastModifiedDate: playlistData.lastModifiedDate,
    userLikes: playlistData.likes.map((user) => shortUserFrom(user)),
    duration: playlistData.duration,
  }
}

export const userFrom = (userData) => {
  return {
    name: userData.displayName,
    id: userData.id,
    image: userData.image,
    playlists: userData.myPlaylist.map((playlist) => playlistFrom(playlist)),
    playlistLikes: userData.likes.map((playlist) => playlistFrom(playlist)),
  }
}
