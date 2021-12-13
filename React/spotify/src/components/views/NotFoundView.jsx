import React from 'react'
import { useNavigate } from 'react-router-dom'
import errorImg from '../../assets/errorImg.png'
import './NotFoundView.css'

const NotFoundView = () => {
  const navigate = useNavigate()

  return (
    <>
      <div className='NotFoundContainer'>
        <div className='subheader'>
          <h2>
            404s and <br /> heartbreaks
          </h2>
          <h3>
            We could{"'"}t find the page <br /> you were looking for
          </h3>
          <a
            href='#'
            onClick={() => {
              navigate('/')
            }}
          >
            Go back
          </a>
        </div>
        <img src={errorImg} alt='' />
      </div>
    </>
  )
}

export default NotFoundView
