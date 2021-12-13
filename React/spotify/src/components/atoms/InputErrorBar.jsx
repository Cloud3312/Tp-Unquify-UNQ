import React from 'react'
import './InputErrorBar.css'

const InputErrorBar = (props) => {
  return (
    <div className='error-bar'>
      <p className='error-text'>{props.text}</p>
    </div>
  )
}

export default InputErrorBar
