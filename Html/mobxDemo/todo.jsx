import { observable, action, computed, observe } from 'mobx'

import React, { Component, Fragment } from 'react'

import ReactDOM from 'react-dom'

import PropTypes from 'prop-types'

import { observer, PropTypes as ObservablePropTypes } from 'mobx-react'

class Todo {
	id = Math.random()
	@observable title = ''
	@observable finished = false

	constructor(title) {
		this.title = title
	}

	@action.bound
	toggle() {
		this.finished = !this.finished
	}
}

class Store {
	@observable todos = []

	@action.bound
	createTodo(title) {
		this.todos.unshift(new Todo(title))
	}

	@action.bound
	removeTodo(todo) {
		this.todos.remove(todo)
	}

	@computed
	get left() {
		return this.todos.filter((todo) => !todo.finished).length
	}
}

var store = new Store()

@observer
class TodoItem extends Component {
	static propsTypes = {
		todo: PropTypes.shape({
			id: PropTypes.number.isRequired,
			title: PropTypes.string.isRequired,
			finished: PropTypes.bool.isRequired
		}).isRequired
	}

	handleClick = (e) => {
		this.props.todo.toggle()
	}

	render() {
		const todo = this.props.todo
		console.log(todo, 'todo')
		return (
			<Fragment>
				<input
					type="checkbox"
					className="toggle"
					onClick={this.handleClick}
					// readOnly={todo.finished}
					// onChange={todo.finished}
					defaultChecked={todo.finished}
				/>
				<span className={[ 'title', todo.finished && 'finished' ].join(' ')}>{todo.title}</span>
			</Fragment>
		)
	}
}

@observer
class TodoList extends Component {
	static propTypes = {
		store: PropTypes.shape({
			createTodo: PropTypes.func,
			todos: ObservablePropTypes.observableArrayOf(ObservablePropTypes.objectOrObservableObject).isRequired
		}).isRequired
	}

	state = {
		inputValue: ''
	}

	handleSubmit = (e) => {
		e.preventDefault()

		var store = this.props.store
		var inputValue = this.state.inputValue
		store.createTodo(inputValue)
		this.setState({ inputValue: '' })
	}

	handleChange = (e) => {
		var inputValue = e.target.value
		this.setState({
			inputValue
		})
		console.log(3)
	}

	render() {
		const store = this.props.store
		const todos = store.todos
		return (
			<div className="todo-list">
				<header>
					<form onSubmit={this.handleSubmit}>
						<input
							type="text"
							onChange={this.handleChange}
							value={this.state.inputValue}
							className="input"
							placeholder="what needs to do"
						/>
					</form>
				</header>
				<ul>
					{todos.map((todo) => {
						return (
							<li className="todo-item" key={todo.id}>
								<TodoItem todo={todo} />
								<span className="delete" onClick={(e) => store.removeTodo(todo)}>
									X
								</span>
							</li>
						)
					})}
				</ul>
				<footer>{store.left} items unfinished</footer>
			</div>
		)
	}
}

ReactDOM.render(<TodoList store={store} />, document.querySelector('#root'))
