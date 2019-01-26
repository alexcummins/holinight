# api/urls.py

from django.conf.urls import url, include
from rest_framework.urlpatterns import format_suffix_patterns
from .views import CreateView
from .views import DetailsView

url(r'^Party/(?P<pk>[0-9]+)/$',
        DetailsView.as_view(), name="details"),


urlpatterns = {
    url(r'^Party/$', CreateView.as_view(), name="create"),
}

urlpatterns = format_suffix_patterns(urlpatterns)