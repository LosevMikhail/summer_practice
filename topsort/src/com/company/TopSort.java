package com.company;

import java.util.*;

public class TopSort {
    private Graph graph;
    private int V;
    private int E;
    private boolean[] used;


    private Stack<Integer> stack;
    public LinkedList<Integer> ans = new LinkedList<>();

    private Stack<Integer> nextToVisit = new Stack<>();

    private int lastVertex = 0;

    //private int nextToVisit;


    public TopSort(Graph g){
        graph = g;
        init();
    }

    private void init(){
        V = graph.V();
        E = graph.E();
        used = new boolean[graph.VertexList().size()+1];
        stack = new Stack<>();
        for(int i = 0;i<graph.VertexList().size()+1;i++)
            used[i] = false;
    }
/*
    private void DFS(int pos){
        used[pos] = true;
        System.out.println(pos);
        for(int i = 0;i<graph.checkV(pos).way.size();i++){
            if(!used[graph.checkV(pos).way.get(i)])
                DFS(graph.checkV(pos).way.get(i));
        }
        stack.push(pos);
    }
    void alg() {
        init();
        stack.clear();
        ans.clear();
        for(int i = 0;i<graph.VertexList().size();i++){
            if(!used[graph.VertexList().get(i)]) {
                DFS(graph.VertexList().get(i));
            }
        }
        int k = stack.size();
        for(int i = 0;i<k;i++) {
            ans.add(stack.peek());
            System.out.println(stack.peek());
            stack.pop();
        }
    }

 */


    boolean alg(){
        boolean Cycle = false;
        for(int i = 0; i < graph.V();i++){
            Cycle = DFS(graph.VertexList().get(i));
            if(Cycle) {
                return false;
            }
        }
        int k = stack.size();
        for(int i = 0;i<k;i++){
            ans.add(stack.peek());
            stack.pop();
        }
        return true;
    }



    private boolean DFS(int v){
        if(graph.checkV(v).c == 1) {
            System.out.println("CYCLE: " + v);
            return true;

        }
        if(graph.checkV(v).c == 2) {
            return false;
        }
        graph.checkV(v).c = 1;
        System.out.println("красим "+v+" в серый цвет");
        for (int i = 0; i < graph.checkV(v).way.size(); i++){
            if(DFS(graph.checkV(v).way.get(i))) {
                return true;
            }
        }
        stack.push(v);
        graph.checkV(v).c = 2;
        System.out.println("красим " + v + " в черный цвет");
        return false;
    }

    public boolean stepDFS() {
        int currentVertex = nextToVisit.pop();

        System.out.println("Начало шага. вершина " + currentVertex);
        System.out.println("Стек: " + nextToVisit);

        if (graph.checkV(currentVertex) == null) {
            System.out.println("Нет вершины " + currentVertex);
            System.out.println("Выходим из шага ");
            return false;
        }

        if (graph.checkV(currentVertex).c == 1) {
            System.out.println("CYCLE: " + currentVertex);
            return true;
        }

        if (graph.checkV(currentVertex).c == 2) {
            return false;
        }

        graph.checkV(currentVertex).c = 1;
        System.out.println("Красим вершину " + currentVertex + " в серый цвет");

        //System.out.println(allBlack(graph.checkV(currentVertex).way));
        if (allBlack(graph.checkV(currentVertex).way)) {
            // если все потомки вершины черные, красим в черный
            graph.checkV(currentVertex).c = 2;
            stack.push(currentVertex);
            System.out.println("Красим вершину " + currentVertex + " в черный цвет");
        }

        for (int i = 0; i < graph.checkV(currentVertex).way.size(); i++) { // добавили в стек все смежные вершины (-->)
            nextToVisit.push(graph.checkV(currentVertex).way.get(i));
            System.out.println("Добавляем вершину  " + nextToVisit.peek() + " в стек");
            System.out.println("Стек: " + nextToVisit);

        }

        if (allVisited(nextToVisit)) { // докрашиваем в черный
            for (int i = 0; i < graph.V(); i++) {
                // не до v  а до макс. номера вершины
                if (graph.checkV(graph.VertexList().get(i)).c == 1) {
                    graph.checkV(graph.VertexList().get(i)).c = 2;
                    stack.push(graph.VertexList().get(i));

                    System.out.println("Красим вершину " + i + " в черный цвет");
                }
            }
        }

 
        return false;
    }

    private boolean allBlack(Collection<Integer> vertexes) {
        boolean answ = true;
        for (Integer integer : vertexes) {
            if (graph.checkV(integer).c != 2) {
                answ = false;
            }
        }
        return answ;
    }

    private boolean allVisited(Collection<Integer> vertexes) {
        boolean answ = true;
        for (Integer integer : vertexes) {
            if (graph.checkV(integer).c == 0) {
                answ = false;
            }
        }
        return answ;
    }



    boolean doAllSteps(){
        used = new boolean[graph.V()];
        System.out.println("Start doing steps---------------");

        for(int i = 0; i < graph.V();i++) {
            if (graph.checkV(graph.VertexList().get(i)).c != 0 )
                continue;
            nextToVisit.push(i);
            while (!nextToVisit.empty()) {
                if(stepDFS()) {
                    return false;
                }
            }
        }

        int k = stack.size();
        for(int i = 0; i < k; i++){
            ans.add(stack.peek());
            stack.pop();
        }

        System.out.println("Ответ: " + ans);
        return true;
    }

    boolean step() {
        if (allVisited(graph.VertexList())) {
            int k = stack.size();
            for(int i = 0; i < k; i++){
                ans.add(stack.peek());
                stack.pop();
            }
            System.out.println(ans);
            return true;
        }
        if (!nextToVisit.empty()) {
            return stepDFS();
        }
        while (graph.checkV(lastVertex) == null || graph.checkV(lastVertex).c != 0 ) { // == 2
            lastVertex++;
        } // пропускаем вершины которые просматривать не надо потому что они просмотрены
        nextToVisit.push(lastVertex);
        return stepDFS();


    }

    void alg(Graph g){
        graph = g;
        ans.clear();
        stack.clear();
        for(int i = 0;i<graph.V();i++){
            graph.checkV(graph.VertexList().get(i)).c = 0;
        }
        alg();
    }
}