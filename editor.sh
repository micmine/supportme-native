#!/bin/sh

SESSION="supportme-native"
SESSIONEXISTS=$(tmux list-sessions | grep $SESSION)

if [ "$SESSIONEXISTS" = "" ]
then
	tmux new-session -d -s $SESSION

	tmux rename-window -t $SESSION:1 'edit'
	tmux send-keys -t 'edit' 'nvim' C-m

	tmux new-window -t $SESSION:2 -n 'run'
	tmux send-keys -t 'run' ". ~/.bashrc && gradle" C-m

	tmux new-window -t $SESSION:3 -n 'shell'
	tmux send-keys -t 'shell' ". ~/.bashrc" C-m
fi

tmux attach-session -t $SESSION:1
