import React from 'react'
import Card from '../atoms/Card'
import './CardRow.css'

const CardRow = ({ items, title, circle, type }) => {
  return (
    <div className='container'>
      <h3 className='row-title'>{title}</h3>
      <div className='row'>
        {items.map((item, index) => (
          <Card
            key={`${index}. ${item.id}`}
            image={item.image}
            title={item.name}
            circle={circle}
            id={item.id}
            type={type}
          />
        ))}
      </div>
    </div>
  )
}

export default CardRow
