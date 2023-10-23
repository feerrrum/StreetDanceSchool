package org.example.commands;

public class ResponseHandler {
    private final String message;
    public ResponseHandler(String m){message = m;}
    public String setResponse() {
        return switch (message) {
            case ("/start") -> "��� ��� ��� ������ ������";
            case ("���������� �� �������") -> "������ ���������:";
            case ("������ �������") -> "����� �������";
            case ("����������") -> "*��������� � �����������*";
            case ("���������") -> "� ��� �������� 0 �������";
            case ("��������") -> "��������";
            case ("�����") -> "�����";
            default -> "what??";
        };
    }
}