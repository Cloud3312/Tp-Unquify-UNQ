import React from 'react'
import { useNavigate } from 'react-router-dom'
import './Card.css'
const Card = ({ image, title, circle, id, type }) => {
  const navigate = useNavigate()

  return (
    <div className='card' onClick={() => navigate(`/${type}/${id}`)}>
      <img className={`image ${circle && 'image-circle'}`} src={image} />
      <div className='card-title'>{title}</div>
      <div className='card-subtitle'>{title}</div>
    </div>
  )
}

export default Card
